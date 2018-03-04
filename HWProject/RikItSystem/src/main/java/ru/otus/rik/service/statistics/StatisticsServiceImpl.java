package ru.otus.rik.service.statistics;

import ru.otus.rik.domain.StatisticsEntity;
import ru.otus.rik.service.helpers.PersistenceServiceHolder;

import javax.management.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StatisticsServiceImpl implements StatisticsService {
    private final static int MAX_CLIENT_NAME_SIZE = 49;
    private final static String DEFAULT_MAKER_NAME = "statMarker";
    private final static String MARKER_ENV_NAME = "STAT_MARKER_NAME";

    private final static String SESSION_ATTR_STAT_ID = "prevIdStat";

    private final static String DATE_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";
    private final static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    private final EnableStatisticsMBean enableStatistics;
    private Set<StatisticsListener> listeners;

    public StatisticsServiceImpl() {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        listeners = ConcurrentHashMap.newKeySet();

        enableStatistics = new EnableStatisticsImpl();
        try {
            StandardMBean mbean = new StandardMBean(enableStatistics, EnableStatisticsMBean.class);
            ObjectName mbeanName = new ObjectName("ru.otus.rik.statistics:type=EnableStatisticsMBean");
            mbs.registerMBean(mbean, mbeanName);
        } catch (MalformedObjectNameException |
                InstanceAlreadyExistsException |
                MBeanRegistrationException |
                NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getEnabled() {
        return enableStatistics.getEnabled();
    }

    @Override
    public int processStatistics(HttpServletRequest request) throws ProcessStatisticsException, StatisticsDisabledException {

        if (!enableStatistics.getEnabled()) {
            throw new StatisticsDisabledException("Statistics is disabled");
        }
        String markerName = System.getenv(MARKER_ENV_NAME);
        String userTime = request.getParameter("userTime");
        String pageName = request.getParameter("page");
        String prevId = request.getParameter("prevId");
        String clientName = request.getHeader("user-agent");
        String clientIP = request.getRemoteAddr();
        String origin = request.getHeader("origin");

        String serverTime = dateFormat.format(Calendar.getInstance().getTime());

        StatisticsEntity statistics = new StatisticsEntity();
        statistics.setMarkerName(markerName != null ? markerName : DEFAULT_MAKER_NAME);
        statistics.setPageName(pageName);
        statistics.setClientName(clientName.substring(0, MAX_CLIENT_NAME_SIZE));
        statistics.setClientIP(clientIP);
        statistics.setOrigin(origin);

        statistics.setClientTime(userTime);
        statistics.setServerTime(serverTime);

        /* disable cookie style id saving */
//        try {
//            statistics.setPrevIdStat(Integer.valueOf(prevId));
//        } catch (NumberFormatException e) {}

        /* use session style id saving */
        statistics.setPrevIdStat(getSessionData(request));

        StatisticsEntity saved = PersistenceServiceHolder.getPersistenceService().saveStatistics(statistics);
        if (saved == null) {
            throw new ProcessStatisticsException("Failed to save statistics");
        }

        notifyListeners(saved);

        setSessionData(request, saved.getIdStat());
        return saved.getIdStat();
    }

    @Override
    public List<StatisticsEntity> getAllStatistics() {
        return PersistenceServiceHolder.getPersistenceService().findAllStatistics();
    }

    @Override
    public void addListener(StatisticsListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(StatisticsListener listener) throws StatisticsListenerNotFoundException {
        if (!listeners.remove(listener))
            throw new StatisticsListenerNotFoundException("Specified listener not registered");
    }

    private void notifyListeners(StatisticsEntity statistics) {
        List<StatisticsEntity> toSend = new ArrayList<>(1);
        toSend.add(statistics);
        listeners.forEach(listener -> listener.onUpdateStatistics(toSend));
    }

    @Override
    public void processOptions(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST");
        response.addHeader("Access-Control-Request-Headers", "");
    }

    private Integer getSessionData(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }
        return (Integer) session.getAttribute(SESSION_ATTR_STAT_ID);
    }

    private void setSessionData(HttpServletRequest request, Integer data) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(SESSION_ATTR_STAT_ID, data);
        }
    }
}

package ru.otus.rik.service.statistics;

import ru.otus.rik.domain.StatisticsEntity;
import ru.otus.rik.service.persistence.PersistenceService;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.management.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Remote(StatisticsService.class)
@LocalBean
public class StatisticsServiceImpl implements StatisticsService {
    private final static int MAX_CLIENT_NAME_SIZE = 49;
    private final static String DEFAULT_MAKER_NAME = "statMarker";
    private final static String MARKER_ENV_NAME = "STAT_MARKER_NAME";

    private final static String SESSION_ATTR_STAT_ID = "prevIdStat";

    private final static String DATE_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";
    private final static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    private final EnableStatisticsMBean enableStatistics;
    private Set<StatisticsListener> listeners;

    @EJB
    private PersistenceService persistenceService;

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
    public int processStatistics(StatisticsData data) throws ProcessStatisticsException, StatisticsDisabledException {

        if (!enableStatistics.getEnabled()) {
            throw new StatisticsDisabledException("Statistics is disabled");
        }

        String markerName = System.getenv(MARKER_ENV_NAME);

        String serverTime = dateFormat.format(Calendar.getInstance().getTime());

        StatisticsEntity statistics = new StatisticsEntity();
        statistics.setMarkerName(markerName != null ? markerName : DEFAULT_MAKER_NAME);
        statistics.setPageName(data.getPageName());
        statistics.setClientName(data.getClientName().substring(0, MAX_CLIENT_NAME_SIZE));
        statistics.setClientIP(data.getClientIP());
        statistics.setOrigin(data.getOrigin());

        statistics.setClientTime(data.getUserTime());
        statistics.setServerTime(serverTime);

        /* use session style id saving */
        //statistics.setPrevIdStat(getSessionData(data));

        StatisticsEntity saved = persistenceService.saveStatistics(statistics);
        if (saved == null) {
            throw new ProcessStatisticsException("Failed to save statistics");
        }

        notifyListeners(saved);

        //setSessionData(data, saved.getIdStat());
        return saved.getIdStat() == null ? 0 : saved.getIdStat();
    }

    @Override
    public List<StatisticsEntity> getAllStatistics() {
        return persistenceService.findAllStatistics();
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

function getStatistics(pageName) {
    var urlCtl = "/statistics";
    var self = this;       
    var statusTimeout = 300000; // timeout form message
    var pageName = pageName;
        
    query();

    //self.timer = setInterval(query, self.period);

    function query() {
        var request = jQuery.param([
            {name: "userTime", value: new Date().toUTCString()},
            {name: "prevId", value: getCookie("prevId")},
            {name: "page", value: pageName}
            ]);

        $.ajax({
            url: urlCtl,
            cache: false,
            data: request,
            dataType: "json",
            timeout: statusTimeout,
            type: "post",
            success: updateId});
    }

    function updateId(data) {
        var prevId = data.id;
        setCookie("prevId", prevId, 1);
    }

    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires="+ d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    function getCookie(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for(var i = 0; i <ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }
}
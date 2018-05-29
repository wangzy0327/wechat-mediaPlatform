function getDateDiff(dateTimeStamp){
    var minute = 1000 * 60;      //把分，时，天，周，半个月，一个月用毫秒表示
    var hour = minute * 60;
    var day = hour * 24;
    var week = day * 7;
    var halfamonth = day * 15;
    var month = day * 30;
    var curTime = new Date();   //获取当前时间
    var now = Date.parse(curTime);   //获取当前时间毫秒
    console.log(now);

    var Cyear = curTime.getFullYear();
    var CMouth = curTime.getMonth() + 1;
    var CDate = curTime.getDate();

    var diffValue = now - dateTimeStamp;//时间差

    if(diffValue < 0){
        return;
    }
    var minC = diffValue/minute;  //计算时间差的分，时，天，周，月
    var hourC = diffValue/hour;
    var dayC = diffValue/day;
    var weekC = diffValue/week;
    var monthC = diffValue/month;

    var datetime = new Date();
    datetime.setTime(dateTimeStamp);
    var Nyear = datetime.getFullYear();
    // var Nmonth = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    // var Ndate = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    // var Nhour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    // var Nminute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    // var Nsecond = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();


    var Nmonth = datetime.getMonth() + 1;
    var Ndate = datetime.getDate();
    var Nhour = datetime.getHours();
    var Nminute = datetime.getMinutes();
    var Nsecond = datetime.getSeconds();


    console.log("Nyear:"+Nyear);
    console.log("Nmonth:"+Nmonth);
    console.log("Ndate:"+Ndate);
    console.log("Nhour:"+Nhour);
    console.log("Nminute:"+Nminute);
    // if(monthC >= 1 && monthC <= 3){
    //     result = " " + parseInt(monthC) + "月前"
    // }else if(weekC >= 1 && weekC <= 3){
    //     result = " " + parseInt(weekC) + "周前"
    // }else if(dayC >= 1 && dayC <= 6){
    //     result = " " + parseInt(dayC) + "天前"
    // }else

    if(hourC >= 1 && hourC <= 23){
        console.log("Ndate:"+Ndate+" CDate:"+CDate);
        if(hourC < 5)
            result = " " + parseInt(hourC) + "小时前";
        else if(Ndate!=CDate){
            result = " 昨天 " + Nhour + ":" + Nminute;
        }else{
            result = " 今天 " + Nhour + ":" + Nminute;
        }
    }else if(minC >= 1 && minC <= 59){
        result =" " + parseInt(minC) + "分钟前"
    }else if(diffValue >= 0 && diffValue <= minute){
        result = "刚刚"
    }else if( Cyear != Nyear ){
        result = Nyear + "年" + Nmonth + "月" + Ndate + "日";
    }else if(Cyear == Nyear){
        result = Nmonth + "月" + Ndate+ "日";
    }
    return result;
};
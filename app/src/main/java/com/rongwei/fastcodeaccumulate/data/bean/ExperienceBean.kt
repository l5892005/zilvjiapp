package com.rongwei.fastcodeaccumulate.data.bean

class ExperienceBean {


    /**
     * data : [{"contentSub":"人生经验老如狗","contentText":"人生经验老如狗","createTime":1588163515000,"exId":1,"isOpen":0,"uId":5,"userName":"luod"}]
     * max_page : 1
     * total : 1
     */

    var max_page: Int = 0
    var total: Int = 0
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * contentSub : 人生经验老如狗
         * contentText : 人生经验老如狗
         * createTime : 1588163515000
         * exId : 1
         * isOpen : 0
         * uId : 5
         * userName : luod
         */

        var contentSub: String? = null
        var contentText: String? = null
        var createTime: Long = 0
        var exId: Int = 0
        var isOpen: Int = 0
        var uId: Int = 0
        var userName: String? = null
    }
}

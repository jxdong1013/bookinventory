using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace bookweb.beans
{
    public class BookShelfAdapt
    {
        /***
     * 图书编号
     */
        public String bookCode { get; set; }
        /**
         * 图书名称
         */
        public String bookName { get; set; }
        /**
         * 架编号
         */
        public String shelfCode { get; set; }
        /**
         * 架名称
         */
        public String shelfName { get; set; }
        /**
         * 调整时间
         */
        public String adaptTime { get; set; }
        /**
         * 操作人
         */
        public String userName { get; set; }
        /**
         *操作人
         */
        public String userId { get; set; }

    }
}
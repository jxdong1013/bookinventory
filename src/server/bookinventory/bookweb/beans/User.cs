using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace bookweb.beans
{
    public class User
    {
        public int userid { get; set; }
        public string username { get; set; }

        public string password { get; set; }
        //public string token { get; set; }
        /// <summary>
        /// 用户类型: admin , user
        /// </summary>
        public string type { get; set; }
        public string realname { get; set; }
        public string phone { get; set; }
        
        public DateTime createtime { get; set; }
    }
}
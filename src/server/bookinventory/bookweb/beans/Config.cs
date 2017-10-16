using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace bookweb.beans
{
    public class Config
    {
        public static string LoginCookieKey = "bookweb.token";

        public static int success_code = 1;
        public static int login_code = 1000;
        public static int logout_code = 1010;
        public static int error_code = 400;

        public static string book_status_in = "in";
        public static string book_status_out = "out";

    }
}
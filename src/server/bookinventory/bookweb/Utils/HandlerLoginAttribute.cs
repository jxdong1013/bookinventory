using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using bookweb.beans;

namespace bookweb.Utils
{
    public class HandlerLoginAttribute : AuthorizeAttribute
    {
        public bool ignore = false;
        public HandlerLoginAttribute(bool ignore = false)
        {
            this.ignore = ignore;
        }

        protected override void HandleUnauthorizedRequest(AuthorizationContext filterContext)
        {
            base.HandleUnauthorizedRequest(filterContext);
        }

        protected override bool AuthorizeCore(HttpContextBase httpContext)
        {
            //return base.AuthorizeCore(httpContext);

            if (ignore == true) { return true; }

            string cookie = WebHelper.GetCookie(Config.LoginCookieKey);
            User user = DESEncrypt.Decrypt(cookie).ToObject<User>();
            if (user == null)
            {
                DataBase<Object> data = new DataBase<object>();
                data.code = 1000;
                data.message = "login";            
                httpContext.Response.Write(data.ToJson());
                httpContext.Response.End();
                return false;
            }

            return true;
        }



        public override void OnAuthorization(AuthorizationContext filterContext)
        {
            base.OnAuthorization(filterContext);
        }
    }
}
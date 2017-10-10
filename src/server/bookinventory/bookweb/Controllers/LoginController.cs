using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using bookweb.Utils;
using bookweb.beans;

namespace bookweb.Controllers
{
    public class LoginController : Controller
    {
        // GET: Login
        public ActionResult Index()
        {
            return View();
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="username"></param>
        /// <param name="password"></param>
        /// <returns></returns>
        public JsonResult Login(String username , string password)
        {
            JsonResult result = new JsonResult();
            result.JsonRequestBehavior = JsonRequestBehavior.AllowGet;
            DataBase<User> data = new DataBase<beans.User>();
            //todo
            if (username!="jxd" && password != "123456")
            {               
                data.code = Config.error_code;
                data.message = "用户名或密码错误";
                result.Data = data;
                return result;
            }


            User user = new User();
            user.token = Guid.NewGuid().ToString();
            user.userId = user.token;
            user.userName = "jinxiangdong";

            WebHelper.WriteCookie( Config.LoginCookieKey , DESEncrypt.Encrypt(user.ToJson()), 60*24*30);//过期时间一个月60 * 24 * 30


            data.code = Config.success_code;
            data.message = "success";
            data.data = user;           
            result.Data = data;

            return result;

        }

        public JsonResult logout()
        {
            Session.Abandon();
            Session.Clear();
            WebHelper.RemoveCookie(Config.LoginCookieKey);

            JsonResult result = new JsonResult();
            result.JsonRequestBehavior = JsonRequestBehavior.AllowGet;
            DataBase<Object> data = new DataBase<object>();
            data.code = Config.logout_code;
            data.message = "logout";
            result.Data = data;
            return result;
        }

    }
}
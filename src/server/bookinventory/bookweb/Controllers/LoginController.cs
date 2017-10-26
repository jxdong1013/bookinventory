using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using bookweb.Utils;
using bookweb.Models;
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
        public JsonResult login(String username, string password){
            MyJsonResult result = new MyJsonResult();
            result.JsonRequestBehavior = JsonRequestBehavior.AllowGet;
            DataBase<User> data = new DataBase<User>();

            UserModel userModel = new UserModel();
            User user = userModel.login(username, password);

            if (user == null)
            {
                data.code = Config.error_code;
                data.message = "登录名或密码错误";
                data.data = null;
                result.Data = data;
                return result;
            }

            WebHelper.WriteCookie(Config.LoginCookieKey, DESEncrypt.Encrypt(user.ToJson()), 60 * 24 * 30);//过期时间一个月60 * 24 * 30

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

        [HttpPost]
        public JsonResult register(User model){
         
            UserModel userModel = new UserModel();
            MyJsonResult result = new MyJsonResult();
            result.JsonRequestBehavior = JsonRequestBehavior.AllowGet;
            DataBase<User> data = new DataBase<User>();

            if (model == null)
            {
                data.code = Config.error_code;
                data.message ="注册失败";
                data.data = null;
                result.Data = data;
                return result;
            }

            bool isok = userModel.register(model);        
            data.code = isok ? Config.success_code : Config.error_code;
            data.message = isok ? "注册成功":"注册失败";
            data.data = null;
            result.Data = data;
            return result;
        }

    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Threading.Tasks;
using bookweb.Models;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;

namespace bookweb.Controllers
{
    public class RoleController : Controller
    {
        ApplicationRoleManager _appRoleManager = null;

        public ApplicationRoleManager AppRoleManager
        {
            get
            {
                if (_appRoleManager == null)
                {
                    _appRoleManager = HttpContext.GetOwinContext().Get<ApplicationRoleManager>();
                }
                return _appRoleManager;
            }
        }

        // GET: Role
        public ActionResult Index()
        {
            List<ApplicationRole> list = AppRoleManager.Roles.ToList();
            ViewBag.Roles = list;

            return View();
        }

        public ActionResult AddRole()
        {
            //ApplicationRole role = new ApplicationRole();
            RoleViewModel model = new RoleViewModel();
            

            return View(model);
        }

        [HttpPost]        
        [ValidateAntiForgeryToken]
        public  async Task<ActionResult> AddRole(ApplicationRole model )
        {
            if (ModelState.IsValid)
            {
                model.Id = System.Guid.NewGuid().ToString();

                var result = await AppRoleManager.CreateAsync(model);
                if(result.Succeeded)
                {
                    return RedirectToAction("index","role");
                }
                AddErrors(result);

            }          

            return View(model);
        }

        private void AddErrors(IdentityResult result)
        {
            foreach (var error in result.Errors)
            {
                ModelState.AddModelError("", error);
            }
        }

    }


}
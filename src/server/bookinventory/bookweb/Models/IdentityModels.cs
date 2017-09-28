using System.Data.Entity;
using System.Security.Claims;
using System.Threading.Tasks;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;

namespace bookweb.Models
{
    // 可以通过向 ApplicationUser 类添加更多属性来为用户添加配置文件数据。若要了解详细信息，请访问 http://go.microsoft.com/fwlink/?LinkID=317594。   
    public class ApplicationUser : IdentityUser<string, ApplicationUserLogin , ApplicationUserRole , ApplicationUserClaims> 
    {
        public async Task<ClaimsIdentity> GenerateUserIdentityAsync(UserManager<ApplicationUser , string> manager)
        {
            // 请注意，authenticationType 必须与 CookieAuthenticationOptions.AuthenticationType 中定义的相应项匹配
            var userIdentity = await manager.CreateIdentityAsync(this, DefaultAuthenticationTypes.ApplicationCookie);
            // 在此处添加自定义用户声明
            return userIdentity;
        }    

    }

    public class ApplicationDbContext : IdentityDbContext<ApplicationUser  , ApplicationRole , string, ApplicationUserLogin , ApplicationUserRole, ApplicationUserClaims>
    {
        public ApplicationDbContext()
            : base("DefaultConnection")
        {
        }



        public static ApplicationDbContext Create()
        {
            return new ApplicationDbContext();
        }


        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<ApplicationUser>().ToTable("t_users");
            modelBuilder.Entity<ApplicationRole>().ToTable("t_roles");
            modelBuilder.Entity<ApplicationUserRole>().ToTable("t_userroles");
            modelBuilder.Entity<ApplicationUserLogin>().ToTable("t_userlogins");
            modelBuilder.Entity<ApplicationUserClaims>().ToTable("t_userclaims");

        }


    }


    public class ApplicationRole : IdentityRole<string, ApplicationUserRole>
    {
        public string Description { get; set; }
        
    }

    public class ApplicationUserRole : IdentityUserRole<string>
    {

    }


    public class ApplicationUserLogin : IdentityUserLogin<string>
    {

    }

    public class ApplicationUserClaims : IdentityUserClaim<string> { }
      

}
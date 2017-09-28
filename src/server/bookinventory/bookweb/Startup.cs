using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(bookweb.Startup))]
namespace bookweb
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}

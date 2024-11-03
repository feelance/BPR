using System;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.UI;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using ShoeInc.Areas.Identity.Data;
using ShoeInc.Data;

[assembly: HostingStartup(typeof(ShoeInc.Areas.Identity.IdentityHostingStartup))]
namespace ShoeInc.Areas.Identity
{
    public class IdentityHostingStartup : IHostingStartup
    {
        public void Configure(IWebHostBuilder builder)
        {
            builder.ConfigureServices((context, services) =>
            {
                services.AddDbContext<ShoeIncContext>(options =>
                {
                    options.UseSqlServer(
                        context.Configuration.GetConnectionString("DefaultConnection"));
                }, ServiceLifetime.Transient);

                services.AddDefaultIdentity<ShoeIncUser>(options => options.SignIn.RequireConfirmedAccount = true)
                    .AddRoles<IdentityRole>()
                    .AddEntityFrameworkStores<ShoeIncContext>();




            });
           
                   
        }
    }
}
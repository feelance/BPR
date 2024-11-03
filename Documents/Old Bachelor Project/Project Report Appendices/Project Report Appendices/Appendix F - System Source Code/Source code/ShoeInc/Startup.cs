
using System;
using Coravel;
using Firebase.Auth;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Radzen;
using ShoeInc.Areas.Identity;
using ShoeInc.Areas.Identity.Data;
using ShoeInc.Data;
using ShoeInc.Data.DBContext;
using ShoeInc.Data.ShoppingCart;
using ShoeInc.Repositories.Cart;
using ShoeInc.Repositories.Orderlines;
using ShoeInc.Repositories.Orders;
using ShoeInc.Repositories.Products;
using ShoeInc.Repositories.Users;
using ShoeInc.SalesGenerator.OnlineSalesGenerator;
using ShoeInc.SalesGenerator.StoreSalesGenerator;
using ShoeInc.Services;
using ShoeInc.Services.OrderService;
using ShoeInc.Services.ProductService;


namespace ShoeInc
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        // For more information on how to configure your application, visit https://go.microsoft.com/fwlink/?LinkID=398940
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddRazorPages();
            services.AddServerSideBlazor();
            services.AddScoped<LoginService>();
            services.AddScoped<ProductService>();
            var authOptions = Configuration.GetSection("FirebaseAuth").Get<FirebaseAuthOptions>();
            services.AddScoped<IFirebaseAuthService>(u => new FirebaseAuthService(authOptions));
            services.AddScoped<LoginCredentials>();
            services.AddScoped<UserRepository>();
            services.AddScoped<ProductRepository>();
            services.AddScoped<ShoppingCartService>();
            services.AddScoped<AppState>();
            services.AddScoped<CartRepository>();
            services.AddScoped<Cart>();
            // services.AddSingleton<IFirebaseAuthService>(u => new FirebaseAuthService(authOptions));
            services.AddScoped<LoginCredentials>();
            services.AddScoped<UserRepository>();
            services.AddScoped<ProductRepository>(); 
            services.AddScoped<DialogService>();   
            services.AddScoped<NotificationService>();    
            services.AddScoped<TooltipService>();      
            services.AddScoped<ContextMenuService>();
            services.AddScoped<StoreSalesService>();
            services.AddScoped<OrderService>();
            services.AddScoped<OrderRepository>();
            services.AddScoped<OrderlineRepository>();
            
            
            services.AddTransient<StoreSalesGenerator>();
            services.AddTransient<OnlineSalesGenerator>();

            services.AddScheduler();


            
            
           
            
            services
                .AddSingleton<AuthenticationStateProvider, RevalidatingIdentityAuthenticationStateProvider<ShoeIncUser>>();


            // services.AddDbContextFactory<ShoeIncContext>(op =>
            // {
            //     op.UseSqlServer("Server=tcp:shoeincs.database.windows.net;Database=shoeinc;User Id=shoeinc@shoeincs;password=jamflo123.;MultipleActiveResultSets=True;");
            //
            // });

            // var connectionString = "dsds";
            // services.AddDbContext<ShoeIncContext>(op =>
            // {
            //     op.UseSqlServer("Server=tcp:shoeincs.database.windows.net;Database=shoeinc;User Id=shoeinc@shoeincs;password=jamflo123.;MultipleActiveResultSets=True;");
            //
            // });

        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
              
            }
            else
            {
                app.UseExceptionHandler("/Error");
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }

            app.UseHttpsRedirection();
            app.UseStaticFiles();

            app.UseRouting();
            app.UseAuthentication();
            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapBlazorHub();
                endpoints.MapFallbackToPage("/_Host");
            });
            

        }
    }
}
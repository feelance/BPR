using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using ShoeInc.Areas.Identity.Data;
using ShoeInc.Data.ShoppingCart;

namespace ShoeInc.Data
{
    public class ShoeIncContext : IdentityDbContext<ShoeIncUser>
    {
        
        public DbSet<Product> Products { get; set; }
        public DbSet<User> User { get; set; }
        
        public  DbSet<Cart> Carts { get; set; }
        public  DbSet<Orderline> Orderlines { get; set; }
        public  DbSet<Order> Orders { get; set; }
        public ShoeIncContext(DbContextOptions<ShoeIncContext> options)
            : base(options)
        {
        }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);
            // Customize the ASP.NET Identity model and override the defaults if needed.
            // For example, you can rename the ASP.NET Identity table names and more.
            // Add your customizations after calling base.OnModelCreating(builder);
        }
    }
}

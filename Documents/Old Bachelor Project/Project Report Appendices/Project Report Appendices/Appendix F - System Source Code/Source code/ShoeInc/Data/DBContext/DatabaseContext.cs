using Microsoft.EntityFrameworkCore;
using ShoeInc.Data.ShoppingCart;


namespace ShoeInc.Data.DBContext
{
    public class DatabaseContext : DbContext
    {
        public DbSet<Product> Products { get; set; }
        public DbSet<User> User { get; set; }
        public  DbSet<Cart> Carts { get; set; }
        public  DbSet<Orderline> OrderDetails { get; set; }
        public  DbSet<Order> Orders { get; set; }
        
        public DatabaseContext(DbContextOptions opt) : base(opt)
        {
            
        }
        
    }
}



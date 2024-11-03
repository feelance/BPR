using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Components.Server;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.CodeAnalysis.CSharp.Syntax;
using ShoeInc.Areas.Identity.Data;
using ShoeInc.Data;
using ShoeInc.Data.DBContext;
using ShoeInc.Data.ShoppingCart;
using ShoeInc.Repositories.Cart;
using ShoeInc.Repositories.Users;


namespace ShoeInc.Services
{ 
    public class ShoppingCartService
    {


      
        public ShoeIncContext storeDB;
        public CartRepository CartRepository;
        public string username;
        public UserManager<ShoeIncUser> userManager;
        private IHttpContextAccessor _httpContextAccessor;
        public OrderService.OrderService _orderService;
        
        public ShoppingCartService(ShoeIncContext databaseContext, CartRepository repository, IHttpContextAccessor httpContextAccessor, UserManager<ShoeIncUser> userManager,OrderService.OrderService orderService)
        {
      
            storeDB = databaseContext;
            CartRepository = repository;
            this._httpContextAccessor = httpContextAccessor;
            this.userManager = userManager;
            _orderService = orderService;


        }

      
        public static string ShoppingCartId { get; set; } = Guid.NewGuid().ToString();

        public List<Product> getProducts()
        {

            

            List<Product> products = new List<Product>();
            var q = storeDB.Carts.Join(storeDB.Products, c => c.ItemId, p =>p.Id, (c,p) => new 
            {
                brand = p.Brand,
                price = p.Price,
                cartId = c.CartId,
                imageUrl = p.ImageUrl,
                productid = p.Id,
                quantity = c.Count
            }).Where( p => ShoppingCartId == p.cartId);
            
            foreach (var product in q)
            {
                Product p = new Product();
                p.Brand = product.brand;
                p.Price = product.price;
                p.ImageUrl = product.imageUrl;
                p.Id = product.productid;
                p.Quantity = product.quantity;
                products.Add(p);
                
            }


            return products;

        }

        public async  void AddToCart(int itemid)
        {
            
            Cart c = new Cart();
            c.CartId = ShoppingCartId;
            c.ItemId = itemid;
            c.DateCreated = DateTime.Now;
            c.Count = 1;
            if (CartRepository.Exists(c.CartId, c.ItemId))
            {
                Cart cart  =await CartRepository.GetByName(c.CartId, c.ItemId);
                cart.Count = cart.Count + 1;
                CartRepository.Update(cart);
            }
            else
            {
                storeDB.Carts.Add(c);
                storeDB.SaveChanges();
            }
            
        }
        public void CreateOrder(ICollection<Product> carts)
        {
            Cart cart = new Cart();
            cart.CartId = ShoppingCartId;
            cart.Products = carts;
            
             _orderService.CreateCartOrder(cart);
             
             var cartsToDelete =  CartRepository.FindByCondition(c => c.CartId == ShoppingCartId).ToList();
             
             foreach (var x in cartsToDelete)
             {
                 CartRepository.Delete(x);
             }
            
        }

        public async void RemoveFromCart(int productId)
        {
            Cart p = await CartRepository.GetByName(ShoppingCartId, productId);
        
                CartRepository.Delete(p);

                var user = _httpContextAccessor.HttpContext.User;

                var userId = await userManager.FindByEmailAsync(user.Identity.Name);
                
                Console.WriteLine(userId.Id);
                

        }
        
      
       

       
    }
}
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using ShoeInc.Areas.Identity.Data;
using ShoeInc.Data;
using ShoeInc.Data.ShoppingCart;
using ShoeInc.Repositories.Orderlines;
using ShoeInc.Repositories.Orders;
using ShoeInc.Repositories.Products;
using ShoeInc.Repositories.Users;

namespace ShoeInc.Services.OrderService
{
    // Author: Florin Bordei 
    // the purpose of the OrderService is to implement the logical methods 
    public class OrderService
    {

        private OrderRepository OrderRepository;
        private OrderlineRepository OrderlineRepository;
        private ProductRepository ProductRepository;
        private UserRepository UserRepository;
        private List<Orderline> Orderlines = new List<Orderline>(); 
        private IHttpContextAccessor _httpContextAccessor;
        private UserManager<ShoeIncUser> _userManager;

        private readonly List<Orderline> _orderlines = new List<Orderline>(); 
        private List<Product> products = new List<Product>();
        private List<User> _users = new List<User>();
    


        public OrderService(OrderRepository OrderRepository, OrderlineRepository OrderlineRepository,
            ProductRepository ProductRepository, UserRepository UserRepository,IHttpContextAccessor httpContextAccessor,UserManager<ShoeIncUser> userManager, UserRepository userRepository)
        {
            this.OrderlineRepository = OrderlineRepository;
            this.OrderRepository = OrderRepository;
            this.ProductRepository = ProductRepository;
            this.UserRepository = UserRepository;
            _httpContextAccessor = httpContextAccessor;
            _userManager = userManager;
        }

        // Orderline objects are created with the parameters of productId and quantity 
        //Product object is searched through the repository and quantity updated 
        public async Task AddItem(int productId, int quantity)
        {
            var p = await ProductRepository.GetProductById(productId);
            p.StockLevel -= quantity; 
            ProductRepository.UpdateProduct(p);
            Orderline orderline = new Orderline();
            orderline.ProductId = productId;
            orderline.Quantity = quantity;
            orderline.Price = Convert.ToDecimal(p.Price * quantity);
            _orderlines.Add(orderline);
            
            
        }

        //Order object is created and attributes assigned 
        //Value of OrderNumber is passed to the Orderline objects 
        public async Task CreateOrder(int UserId)
        {
            Order order = new Order(); 
            order.Date = DateTime.Today;
            order.UserId = UserId;
            order.OrderNumber = Guid.NewGuid().ToString();
            order.TotalPrice = order.GetTotalPrice();
            order.Orderlines = new List<Orderline>(); 
            order.Orderlines.AddRange(_orderlines);

            foreach (var o in order.Orderlines)
            {
                o.OrderNumber = order.OrderNumber;
            }
        }

        //Method to create rand Order objects to be stored in the database 
        public async Task RandomOrderGenerator()
        {
             for (int i = 0; i < new Random().Next(1,3); i++)
             {
                 products = (List<Product>) await ProductRepository.GetAllProductsAsync();
                var randomProduct = products[new Random().Next(products.Count)];
                 Orderline orderline = new Orderline();
                 orderline.ProductId = randomProduct.Id;
                 orderline.Quantity = new Random().Next(1,4);
                 orderline.Price = Convert.ToDecimal(randomProduct.Price * orderline.Quantity);
                 _orderlines.Add(orderline);
             }

             _users = (List<User>) await UserRepository.GetAllAsync(); 
            
             Order order = new Order(); 
             order.Date = DateTime.Today;
             order.UserId = _users[new Random().Next(_users.Count)].ID;
             order.OrderNumber = Guid.NewGuid().ToString();
             order.Orderlines = new List<Orderline>(); 
             order.Orderlines.AddRange(_orderlines);
             order.TotalPrice = order.GetTotalPrice();
            
             OrderRepository.CreateOrder(order);
            
             foreach (var o in order.Orderlines)
             {
                 o.OrderNumber = order.OrderNumber;
                 OrderlineRepository.createOrderline(o);
             }
            
            
          
        }
        // Author: Jaume Loppez 
        public async Task CreateCartOrder(Cart carts)
        { 
            
            string  orderNumber = Guid.NewGuid().ToString();
            var user = _httpContextAccessor.HttpContext.User;
            var userId = await _userManager.FindByEmailAsync(user.Identity.Name);
            Order order = new Order();
            order.Date = DateTime.Today;
            order.OrderNumber = orderNumber;
            
            var userDetails =  await UserRepository.GetByName(userId.Email);
            if (userDetails != null)
            {
                order.UserId = userDetails.ID;
            }
            
            
            order.Orderlines = new List<Orderline>();
            
          
            order.Orderlines.AddRange(Orderlines);
            
            OrderRepository.CreateOrder(order);
            foreach (var x in carts.Products)
            {
                Orderline ol = new Orderline();
                ol.Price = x.Price;
                ol.Quantity = x.Quantity;
                ol.ProductId = x.Id;
                ol.OrderNumber = orderNumber;
                order.Orderlines.Add(ol);
                OrderlineRepository.createOrderline(ol);
            }
            
        
           

            
        }
        

    }
}
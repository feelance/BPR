using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using ShoeInc.Data;
using ShoeInc.Data.DBContext;

namespace ShoeInc.Repositories.Orders
{
    // Author: Florin Bordei 
    public class OrderRepository : RepositoryBase<Order>, IOrderRepository
    {
        // role of the repository is to access the objects stored in the db 
        public readonly ShoeIncContext context;
        public OrderRepository(ShoeIncContext context) : base(context)
        {
            this.context = context; 
        }
        
        // returns all Order objects stored in the db 
        public async Task<IEnumerable<Order>> GetAllOrdersAsync()
        {
            return await FindAll().OrderBy(o => o.OrderId).ToListAsync();
        }
        
        // returns an unique Order object, searched is based on the userId that placed the order  
        public async Task<IEnumerable<Order>> GetOrdersByCustomer(int UserId)
        {
            return await FindAll().OrderBy(o => o.UserId).ToListAsync();
        }
        // returns an unique Order object, searched is based on the orderNumber of the Orderline object 
        public async Task<Order> GetOrderByOrderNumber(string orderNumber)
        {
            return await FindByCondition(o => o.OrderNumber.Equals(orderNumber)).FirstOrDefaultAsync();
        }

        // stores a new Order object in the database 
        public void CreateOrder(Order order)
        {
            Create(order);
            context.SaveChanges();
        }

        //Order object is updated and saved in the db 
        public void UpdateOrder(Order order)
        {
            throw new NotImplementedException();
        }

        //Order object is deleted and the db is saved 
        public void DeleteOrder(Order order)
        {
            Delete(order);
            context.SaveChanges();        }

        //saves the new context of the database 
        public Task SaveChanges()
        {
            return context.SaveChangesAsync(); 
        }

     
    }
}
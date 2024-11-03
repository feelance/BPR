using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;
using ShoeInc.Data;
using ShoeInc.Data.DBContext;

namespace ShoeInc.Repositories.Orderlines
{
    // Author: Florin Bordei 
    public class OrderlineRepository: RepositoryBase<Orderline>, IOrderlineRepository
    {
        // role of the repository is to access the objects stored in the db 
        public readonly ShoeIncContext context;
        
        public OrderlineRepository(ShoeIncContext context) : base(context)
        {
            this.context = context;
        }

        // returns all Orderline objects stored in the db 
        public async Task<IEnumerable<Orderline>> GetAllOrderlinesAsync(string OrderNumber)
        {
            return await FindAll().Where(o => o.OrderNumber.Equals(OrderNumber)).ToListAsync();
        }

        // returns an unique Orderline object, searched is based on the Id of the Orderline object 
        public async Task<Orderline> GetOrderlineById(int orderlineId)
        {
            return await FindByCondition(o => o.OrderlineId == orderlineId).FirstOrDefaultAsync();
        }

        // stores a new Orderline object in the database 
        public void createOrderline(Orderline orderline)
        {
            if (!OrderlineExists(orderline.OrderlineId))
            {
                Create(orderline);
                context.SaveChanges();
            }
            else
            {
                Console.WriteLine("Orderline already exists");
            }
            
        }

        //Orderline object is updated and saved in the db 
        public void UpdateOrderline(Orderline orderline)
        {
            context.Update(orderline);
            context.SaveChanges();
            
        }

        //Orderline object is deleted and the db is saved 
        public async Task DeleteOrderline(Orderline orderline)
        {
            Delete(orderline);
            context.SaveChanges();
        }

        //checks if an Orderline objects exists in the db 
        public bool OrderlineExists(int OrderlineId)
        {
            return context.Orderlines.Any(o => o.OrderlineId == OrderlineId);
        }
        
        //saves the new context of the database 
        public Task SaveChanges()
        {
            return context.SaveChangesAsync();
        }


     
    }
}
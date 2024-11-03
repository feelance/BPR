using System.Collections.Generic;
using System.Threading.Tasks;
using ShoeInc.Data;

namespace ShoeInc.Repositories.Orderlines
{
    // Author: Florin Bordei 
    public interface IOrderlineRepository : IRepositoryBase<Orderline>
    {
        // interface plays the role of the contract of methods that the OrderlineRepository needs to respect 
        Task<IEnumerable<Orderline>> GetAllOrderlinesAsync(string orderNumber);

        Task<Orderline> GetOrderlineById (int orderlineId);

        void createOrderline(Orderline orderline);

        void UpdateOrderline(Orderline orderline);

        Task DeleteOrderline(Orderline orderline);
        
        bool OrderlineExists(int orderlineId);

        Task SaveChanges();

    }
}
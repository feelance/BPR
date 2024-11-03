using System.Collections.Generic;
using System.Threading.Tasks;
using ShoeInc.Data;

namespace ShoeInc.Repositories.Orders
{
    // Author: Florin Bordei 
    public interface IOrderRepository : IRepositoryBase<Order>
    {
        // interface plays the role of the contract of methods that the OrderRepository needs to respect 
        Task<IEnumerable<Order>> GetAllOrdersAsync();

        Task<IEnumerable<Order>> GetOrdersByCustomer (int userId);
        
        Task<Order> GetOrderByOrderNumber (string orderNumber);

        void CreateOrder(Order order);

        void UpdateOrder(Order order);

        void DeleteOrder(Order order);
        
        Task SaveChanges();

    }
}
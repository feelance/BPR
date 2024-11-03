using System.Collections.Generic;
using System.Threading.Tasks;
using ShoeInc.Data;

namespace ShoeInc.Repositories.Products
{
    // Author: Florin Bordei 
    public interface IProductRespository : IRepositoryBase<Product>
    {
        // interface plays the role of the contract of methods that the OrderRepository needs to respect 
        Task<IEnumerable<Product>> GetAllProductsAsync();

        Task<Product> GetProductByName (string ModelCode);

        Task<Product> GetProductById(int ProductId);

        void CreateProduct(Product product);

        void UpdateProduct(Product product);

        void DeleteProduct(Product product);

        
        bool ProductExists(string ModelCode);

        Task saveChanges();

    }
}
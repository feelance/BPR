using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using ShoeInc.Data;
using ShoeInc.Repositories.Products;
using ShoeInc.SalesGenerator.StoreSalesModel;

namespace ShoeInc.Services
{
    // Author: Florin Bordei 
    // the purpose of the StoreSalesService is to implement the logical methods 

    public class StoreSalesService
    {
        private IList<Product> _products;
        private IList<StoreProduct> _storeProducts;
        private ProductRepository ProductRepository;

        public StoreSalesService(ProductRepository productRepository)
        {
            ProductRepository = productRepository; 
        }

        // the method returns a random Product that will be included in the StoreSales objects 
        public async Task<StoreProduct> RandomStoreProduct()
        {
            _products = new List<Product>();
            _products = (IList<Product>) await ProductRepository.GetAllProductsAsync(); 
            _storeProducts = new List<StoreProduct>(_products.Count);
            
            foreach (var p in _products)
            {
                var storeProduct = new StoreProduct
                {
                    Brand = p.Brand,
                    Model = p.Model,
                    ModelCode = p.ModelCode,
                    Price = Convert.ToDecimal(p.Price),
                    Quantity = new Random().Next(1, 5)
                };

                _storeProducts.Add(storeProduct);

            }

            int index = new Random().Next(_storeProducts.Count);

            var randomProduct = _storeProducts[index];
            
            return randomProduct; 

        }

    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using ShoeInc.Data;
using ShoeInc.Data.DBContext;
using ShoeInc.Repositories.Products;

namespace ShoeInc.Services.ProductService
{
    // Author: Florin Bordei 
    // the purpose of the ProductService is to implement the logical methods 
    public class ProductService
    {
        private ProductRepository _productRepository;
        private IList<Product> _products;

        public ProductService(ProductRepository productRepository)
        {
            this._productRepository = productRepository;
        }

        // checks what products are bellow 200 units in StockLevel 
        public async Task<bool> CheckOutOfStock()
        {
            bool outOfStockWarning = false;
            
            _products =  (IList<Product>) await _productRepository.GetAllProductsAsync();

            foreach (var p in _products)
            {
                if (p.StockLevel < 200)
                {
                    outOfStockWarning = true;
                }
                
            }

            return outOfStockWarning;
        }

        // returns a list of objects that are under 200 units in their stockLevel 
        public async Task<IEnumerable<Product>> ListOutOfStockProducts()
        {
            var tempList = (IList<Product>) await _productRepository.GetAllProductsAsync();

            return tempList.Where(p => p.StockLevel < 200).ToList();

        }
        
        // when products needs to be ordered, the StockLevel attribute is updated 
        public async Task  OrderProduct(string modelCode)
        {
            var prod = await _productRepository.GetProductByName(modelCode);
            prod.StockLevel += 400;
            _productRepository.UpdateProduct(prod);
            await _productRepository.saveChanges(); 
        }


       // when Discount is applied, the method updates the Price of the product 
        public async Task ApplyDiscount(string modelCode, decimal discount)

        {
            var prod = await _productRepository.GetProductByName(modelCode);
            prod.Price -= prod.Price * discount / 100;
            prod.Discount = discount; 
            _productRepository.UpdateProduct(prod);
            await _productRepository.saveChanges(); 
        }
        
        // when Discount is removed, the method returns the product to the original Price 
        public async Task RemoveDiscount(string modelCode, decimal discount)
        {
            var prod = await _productRepository.GetProductByName(modelCode);
            prod.Price = prod.Price * 100 / discount;
            prod.Discount = 0; 
            _productRepository.UpdateProduct(prod);
            await _productRepository.saveChanges();         }

        //returns true or false if a Product object is discounted 
        public async  Task<bool> IsDiscounted(string modelCode)
        {
            var isDiscounted = false;

            var item = await _productRepository.GetProductByName(modelCode);

            if (item.Discount != 0)
            {
                isDiscounted = true;
            }
            return isDiscounted;
        }

       
    }
}
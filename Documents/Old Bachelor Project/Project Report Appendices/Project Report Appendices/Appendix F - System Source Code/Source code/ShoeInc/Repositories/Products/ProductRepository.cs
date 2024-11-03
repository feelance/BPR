using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;
using ShoeInc.Data;
using ShoeInc.Data.DBContext;

namespace ShoeInc.Repositories.Products
{
    // Author: Florin Bordei 
    public class ProductRepository: RepositoryBase<Product>, IProductRespository
    {
        // role of the repository is to access the objects stored in the db 

        private readonly ShoeIncContext _context;
        
        public ProductRepository(ShoeIncContext context) : base(context)
        {
            _context = context;
        }

        // returns all Product objects stored in the db 
        public async Task<IEnumerable<Product>> GetAllProductsAsync()
        {
            
            return await FindAll().OrderBy(prod => prod.Model).ToListAsync();
        }

        // returns an unique Product object, searched is based on modelCode of the Product object
        public async Task<Product> GetProductByName(string modelCode)
        {
            return await FindByCondition(prod => prod.ModelCode.Equals(modelCode)).FirstOrDefaultAsync();
        }
        
        // returns an unique Product object, searched is based on productId of the Product object
        public async Task<Product> GetProductById(int productId)
        {
            return await FindByCondition(prod => prod.Id == productId).FirstOrDefaultAsync();
        }

        // stores a new Product object in the database 
        public void CreateProduct(Product product)
        {
            if (!ProductExists(product.ModelCode))
            {
                Create(product);
                _context.SaveChanges();
            }
            else
            {
                Console.WriteLine("Product already exists in our warehouse");
            }
        }

        //Product object is updated and saved in the db 
        public  void UpdateProduct(Product product)
        {
             _context.Update(product);
             _context.SaveChanges();
        }

        //Product object is deleted and the db is saved 
        public void   DeleteProduct(Product product)
        { 
             Delete(product);
              _context.SaveChanges();
        }

        //Product object is deleted and the db is saved 
        public async Task DeleteProductByModelCode(string modelCode)
        {
            var temp = await GetProductByName(modelCode);
            Delete(temp);
            _context.SaveChanges();
        }

        //checks if an Product objects exists in the db 
        public bool ProductExists(string ModelCode)
        {
            return _context.Products.Any(p => p.ModelCode.Equals(ModelCode));
        }

        //saves the new context of the database 
        public Task saveChanges()
        {
            return _context.SaveChangesAsync();
        }


     
    }
}
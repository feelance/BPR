using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.CodeAnalysis.CSharp;
using Microsoft.EntityFrameworkCore;
using ShoeInc.Data;
using ShoeInc.Data.DBContext;
using ShoeInc.Repositories.Cart;

namespace ShoeInc.Repositories.Cart
{
    public class CartRepository: RepositoryBase<Data.ShoppingCart.Cart>, ICartRepository
    {
        


        public CartRepository(ShoeIncContext context) : base(context)
        {
          
            
        }

        public async Task<IEnumerable<Data.ShoppingCart.Cart>> GetAllAsync()
        {   
            throw new NotImplementedException();
            // return await FindAll().OrderBy(u => u.EmailAddress).ToListAsync();
        }

        public async Task<Data.ShoppingCart.Cart> GetByName(string cartId, int productId)
        {
           
          return await FindByCondition(u => u.CartId.Equals(cartId)).FirstOrDefaultAsync();
          
          //&& u.ItemId.Equals(productId
         
        }

        public new void Create(Data.ShoppingCart.Cart x)
        {
            base.Create(x);
            context.SaveChanges();
          
        }

        public  new void Update(Data.ShoppingCart.Cart x)
        {
            base.Update(x);
            context.SaveChanges();
        }

        public new void Delete(Data.ShoppingCart.Cart x)
        {
            base.Delete(x);
            context.SaveChanges();
        }

        public bool Exists(string cartId, int productid)
        {
            return context.Carts.Any(p => p.ItemId.Equals(productid) && p.CartId.Equals(cartId));
        }

        public Task saveChanges()
        {
            return context.SaveChangesAsync();
        }
    }
}
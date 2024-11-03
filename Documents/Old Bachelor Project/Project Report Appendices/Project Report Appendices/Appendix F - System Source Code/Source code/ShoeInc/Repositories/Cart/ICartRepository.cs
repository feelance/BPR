using System.Collections.Generic;
using System.Threading.Tasks;
using ShoeInc.Data;
using ShoeInc.Data.ShoppingCart;
using ShoeInc.Data.ShoppingCart;


namespace ShoeInc.Repositories.Cart
{
    public interface ICartRepository : IRepositoryBase<Data.ShoppingCart.Cart>
    {
        Task<IEnumerable<Data.ShoppingCart.Cart>> GetAllAsync();

        Task<Data.ShoppingCart.Cart> GetByName (string cartId, int productId);

        void Create(Data.ShoppingCart.Cart u);

        void Update(Data.ShoppingCart.Cart u);

        void Delete(Data.ShoppingCart.Cart u);
        
        bool Exists(string cartID, int productID);

        Task saveChanges();
    }
}
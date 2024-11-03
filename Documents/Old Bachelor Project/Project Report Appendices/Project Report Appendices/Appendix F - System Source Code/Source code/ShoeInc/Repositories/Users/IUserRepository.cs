using System.Collections.Generic;
using System.Threading.Tasks;
using ShoeInc.Data;


namespace ShoeInc.Repositories.Users
{
    public interface IUserRespository : IRepositoryBase<User>
    {
        // interface plays the role of the contract of methods that the OrderRepository needs to respect 
        Task<IEnumerable<User>> GetAllAsync();

        Task<User> GetByName (string ModelCode);

        void Create(User u);

        void Update(User u);

        void Delete(User u);
        
        bool Exists(string ModelCode);

        Task saveChanges();
    }
}
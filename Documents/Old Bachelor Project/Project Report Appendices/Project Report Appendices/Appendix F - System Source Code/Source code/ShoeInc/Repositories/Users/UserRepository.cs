using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using ShoeInc.Data;
using ShoeInc.Data.DBContext;

namespace ShoeInc.Repositories.Users
{
    public class UserRepository: RepositoryBase<User>, IUserRespository
    {
        


        public UserRepository(ShoeIncContext context) : base(context)
        {
            //TODO INJECT CONTEXT 
            
        }

        public async Task<IEnumerable<User>> GetAllAsync()
        {
            return await FindAll().OrderBy(u => u.EmailAddress).ToListAsync();
        }

        public async Task<User> GetByName(string email)
        {
            return await FindByCondition(u => u.EmailAddress.Equals(email)).FirstOrDefaultAsync();
        }

        public new void Create(User u)
        {
            base.Create(u);
            context.SaveChanges();
          
        }

        public  new void Update(User user)
        {
            base.Update(user);
            context.SaveChanges();
        }

        public new void Delete(User user)
        {
            base.Delete(user);
            context.SaveChanges();
        }

        public bool Exists(string email)
        {
            return context.User.Any(p => p.EmailAddress.Equals("email"));
        }

        public Task saveChanges()
        {
            return context.SaveChangesAsync();
        }
    }
}
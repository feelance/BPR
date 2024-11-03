using System;
using System.Linq;
using System.Linq.Expressions;
using Microsoft.EntityFrameworkCore;
using ShoeInc.Data;
using ShoeInc.Data.DBContext;

namespace ShoeInc.Repositories
{
    // Author: Florin Bordei 
    public abstract class RepositoryBase<T> : IRepositoryBase<T> where T: class
    {
        
        protected IDbContextFactory<ShoeIncContext> contextf { get; set; }
        protected ShoeIncContext context { get; set; }
        
        

        public RepositoryBase(ShoeIncContext contextf)
        {
            context = contextf;
        }
        
        
        public IQueryable<T> FindAll()
        {
            return this.context.Set<T>().AsNoTracking();
        }

        public IQueryable<T> FindByCondition(Expression<Func<T, bool>> expression)
        {
            return this.context.Set<T>().Where(expression).AsNoTracking();
        }

        public void Create(T entity)
        {
            this.context.Set<T>().Add(entity);
        }

        public void Update(T entity)
        {
            this.context.Set<T>().Update(entity);
            this.context.Entry(entity).State = EntityState.Detached;

        }

        public void Delete(T entity)
        {
            this.context.Set<T>().Remove(entity);
        }
    }
}
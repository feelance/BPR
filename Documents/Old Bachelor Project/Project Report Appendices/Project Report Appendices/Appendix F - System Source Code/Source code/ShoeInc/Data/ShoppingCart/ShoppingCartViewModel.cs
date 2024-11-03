using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ShoeInc.Data.ShoppingCart
{
    public class ShoppingCartViewModel
    {
      
        public List<Cart> CartItems { get; set; }
        // public decimal CartTotal => CartItems.Sum(c => c.Count * c.Item.Price);
        public decimal CartTotal => CartItems.Sum(c => c.Count * c.Id);
    }
}
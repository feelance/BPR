using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace ShoeInc.Data.ShoppingCart
{
    public partial class Cart
    {
        public int Id { get; set; }
        public string CartId { get; set; }
        public int ItemId { get; set; }
        public int Count { get; set; }
        public DateTime DateCreated { get; set; }

        [NotMapped]
        public ICollection<Product> Products { get; set; }
        



    }
}
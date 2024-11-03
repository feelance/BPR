using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace ShoeInc.Data
{
    public class User
    {
        public User()
        {
        }
        
        [Key]
        public int ID { get; set; }
        
        [Column("email")] 
        public string EmailAddress { get; set; }
        [NotMapped]
        public string Password { get; set; }
        [NotMapped]
        public string? ConfirmPassword { get; set; }
        
        [Column("street")]
        
        public string Street { get; set; }
        [Column("city")]
        public string City { get; set; }
        
        [Column("gender")]
        public string SelectedGender { get; set; }
        [NotMapped]
        public List<SelectListItem> ListofGender { get; set; }
        [Column("age")]
        
        public int Age { get; set; }
        
        
        
        
    }
}
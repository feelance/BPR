using MongoDB.Bson;

namespace ShoeInc.Data
{
    // Author: Florin Bordei 
    public class Employee
    {
        public ObjectId ObjectId { get; set; }
        public string Name { get; set; }
    }
}
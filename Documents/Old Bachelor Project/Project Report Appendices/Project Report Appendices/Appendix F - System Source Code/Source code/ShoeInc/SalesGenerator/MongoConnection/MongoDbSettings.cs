namespace ShoeInc.SalesGenerator.MongoConnection
{
    // Author: Florin Bordei 
    public class MongoDbSettings : IMongoDbSettings
    {

        public MongoDbSettings()
        {
            CollectionName = "dom";
            ConnectionString =
                "mongodb+srv://admin:admin123@shoeinc.aaff6.mongodb.net/shoeinc?retryWrites=true&w=majority";
            DatabaseName = "shoeinc";
        }
        
        
        public string CollectionName { get; set; }
        public string ConnectionString { get; set; }
        public string DatabaseName { get; set; }
        
       
    }
}
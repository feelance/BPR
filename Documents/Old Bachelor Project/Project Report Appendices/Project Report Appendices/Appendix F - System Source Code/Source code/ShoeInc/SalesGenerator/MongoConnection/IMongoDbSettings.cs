namespace ShoeInc.SalesGenerator.MongoConnection
{
    // Author: Florin Bordei 
    public interface IMongoDbSettings
    {
        string CollectionName { get; set; }
         
        string ConnectionString { get; set; }
        string DatabaseName { get; set; } 
    }
}
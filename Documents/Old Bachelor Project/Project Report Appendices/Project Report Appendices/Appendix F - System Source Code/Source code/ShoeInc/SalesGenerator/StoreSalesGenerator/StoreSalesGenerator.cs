using System;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
using Coravel.Invocable;
using Microsoft.Extensions.Logging;
using MongoDB.Bson;
using MongoDB.Driver;
using ShoeInc.SalesGenerator.MongoConnection;
using ShoeInc.SalesGenerator.StoreSalesModel;
using ShoeInc.Services;

namespace ShoeInc.SalesGenerator.StoreSalesGenerator
{
    // Author: Florin Bordei 
    public class StoreSalesGenerator : IInvocable
    {
        // StoreSalesGenerator is using Coravel, with the help of it the task can be scheduled 
        private readonly ILogger<StoreSalesGenerator> Logger;
        private readonly StoreSalesService storeSalesService; 
        static MongoClient client;
        private static IMongoDbSettings _settings;
        private static IMongoDatabase database;

        public StoreSalesGenerator(ILogger<StoreSalesGenerator> Logger, StoreSalesService storeSalesService)
        {
            this.Logger = Logger;
            this.storeSalesService = storeSalesService; 
        }

        public async Task Invoke()
        {
            _settings = new MongoDbSettings();
            client = new MongoClient(_settings.ConnectionString);
            database = client.GetDatabase(_settings.DatabaseName);
            var collection = database.GetCollection<SalesStore>("StoreSales");

            
            RandomDateTime randomDateTime = new RandomDateTime();
            SalesStore salesStore = new SalesStore();
            salesStore.Date = randomDateTime.Next().ToString();
            salesStore.Store = new Store();
            salesStore.Store = salesStore.Store.RandomStore();

            salesStore.Product = new StoreProduct();
            salesStore.Product =  await storeSalesService.RandomStoreProduct();

             collection.InsertOne(salesStore);

             await Task.FromResult(true);
        }
    }
}
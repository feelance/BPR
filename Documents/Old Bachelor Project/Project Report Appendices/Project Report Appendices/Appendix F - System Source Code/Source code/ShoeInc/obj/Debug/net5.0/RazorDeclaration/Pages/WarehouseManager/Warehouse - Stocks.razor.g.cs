// <auto-generated/>
#pragma warning disable 1591
#pragma warning disable 0414
#pragma warning disable 0649
#pragma warning disable 0169

namespace ShoeInc.Pages.WarehouseManager
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Components;
#nullable restore
#line 1 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using System.Net.Http;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Authorization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 3 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Components.Authorization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 4 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Components.Forms;

#line default
#line hidden
#nullable disable
#nullable restore
#line 5 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Components.Routing;

#line default
#line hidden
#nullable disable
#nullable restore
#line 6 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Components.Web;

#line default
#line hidden
#nullable disable
#nullable restore
#line 7 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Components.Web.Virtualization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 8 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.JSInterop;

#line default
#line hidden
#nullable disable
#nullable restore
#line 9 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using ShoeInc;

#line default
#line hidden
#nullable disable
#nullable restore
#line 10 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using ShoeInc.Shared;

#line default
#line hidden
#nullable disable
#nullable restore
#line 11 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using System.IO;

#line default
#line hidden
#nullable disable
#nullable restore
#line 12 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Radzen;

#line default
#line hidden
#nullable disable
#nullable restore
#line 13 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Radzen.Blazor;

#line default
#line hidden
#nullable disable
#nullable restore
#line 4 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/WarehouseManager/Warehouse - Stocks.razor"
using ShoeInc.Data;

#line default
#line hidden
#nullable disable
#nullable restore
#line 5 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/WarehouseManager/Warehouse - Stocks.razor"
using ShoeInc.Repositories.Products;

#line default
#line hidden
#nullable disable
#nullable restore
#line 6 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/WarehouseManager/Warehouse - Stocks.razor"
using ShoeInc.Services.OrderService;

#line default
#line hidden
#nullable disable
#nullable restore
#line 7 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/WarehouseManager/Warehouse - Stocks.razor"
using ShoeInc.Services.ProductService;

#line default
#line hidden
#nullable disable
    [Microsoft.AspNetCore.Components.RouteAttribute("/warehousestocks")]
    public partial class Warehouse___Stocks : Microsoft.AspNetCore.Components.ComponentBase
    {
        #pragma warning disable 1998
        protected override void BuildRenderTree(Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder)
        {
        }
        #pragma warning restore 1998
#nullable restore
#line 80 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/WarehouseManager/Warehouse - Stocks.razor"
      


    IList<Product> _selectedProducts;

    private IList<Product> _products;

    private bool OutOfStockWarning { get; set; }

    private ProductRepository productRepository; 


    protected override async Task OnInitializedAsync()
    {
        OutOfStockWarning = await ProductService.CheckOutOfStock();

        productRepository = new ProductRepository(ContextFactory);

        _products = (IList<Product>) await productRepository.GetAllProductsAsync();
        _selectedProducts = _products.Take(1).ToList();

       
    }

    // changes the row (font) if the product stockLevel is bellow 200 units 
    void RowRender(RowRenderEventArgs<Product> args)
    {
        args.Attributes.Add("style", $"font-weight: {(args.Data.StockLevel < 200 ? "bold" : "normal")};");
    }

    // changes the cell (background color) if the product stockLevel is bellow 200 units 
    void CellRender(DataGridCellRenderEventArgs<Product> args)
    {
        if (args.Column.Property == "StockLevel")
        {
            args.Attributes.Add("style", $"background-color: {(args.Data.StockLevel < 200 ? "red" : "white")};");

            if (args.Data.StockLevel == 0)
            {
                args.Attributes.Add("colspan", 2);
            }
        }


    }



    private void OnAddProduct()
    {
        NavigationManager.NavigateTo("/warehouseaddproduct");
    }

    // product is deleted from the db and the list is refreshed 
    private async void DeleteProduct()
    {
        productRepository = new ProductRepository(ContextFactory);


        foreach (var p in _selectedProducts)
        {
            await productRepository.DeleteProductByModelCode(p.ModelCode);

        }

        NavigationManager.NavigateTo(NavigationManager.Uri, true);


    }



// selected product has its stockLevel updated 
    
    private async void OrderProducts()
    {
        foreach (var p in _selectedProducts)
        {
            await ProductService.OrderProduct(p.ModelCode);

        }
        NavigationManager.NavigateTo(NavigationManager.Uri, true);
        
    }

    void ShowNotification(NotificationMessage message)
    {
        NotificationService.Notify(message);

    }


#line default
#line hidden
#nullable disable
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private OrderService OrderService { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private NotificationService NotificationService { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private ProductService ProductService { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private ShoeIncContext ContextFactory { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private NavigationManager NavigationManager { get; set; }
    }
}
#pragma warning restore 1591

#pragma checksum "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Shared/NavMenu.razor" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "ca139da6049a05579688491d7f365d9b428630e2"
// <auto-generated/>
#pragma warning disable 1591
namespace ShoeInc.Shared
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
    public partial class NavMenu : Microsoft.AspNetCore.Components.ComponentBase
    {
        #pragma warning disable 1998
        protected override void BuildRenderTree(Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder)
        {
            __builder.OpenElement(0, "div");
            __builder.AddAttribute(1, "class", "top-row pl-4 navbar topnavbar");
            __builder.AddAttribute(2, "b-mrsrnueiu3");
            __builder.AddMarkupContent(3, "<a class=\"navbar-brand\" href b-mrsrnueiu3>ShoeInc</a>\n    ");
            __builder.OpenElement(4, "button");
            __builder.AddAttribute(5, "class", "navbar-toggler");
            __builder.AddAttribute(6, "onclick", Microsoft.AspNetCore.Components.EventCallback.Factory.Create<Microsoft.AspNetCore.Components.Web.MouseEventArgs>(this, 
#nullable restore
#line 6 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Shared/NavMenu.razor"
                                             ToggleNavMenu

#line default
#line hidden
#nullable disable
            ));
            __builder.AddAttribute(7, "b-mrsrnueiu3");
            __builder.AddMarkupContent(8, "<span class=\"navbar-toggler-icon\" b-mrsrnueiu3></span>");
            __builder.CloseElement();
            __builder.CloseElement();
            __builder.AddMarkupContent(9, "\n\n");
            __builder.OpenElement(10, "div");
            __builder.AddAttribute(11, "class", 
#nullable restore
#line 11 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Shared/NavMenu.razor"
             NavMenuCssClass

#line default
#line hidden
#nullable disable
            );
            __builder.AddAttribute(12, "onclick", Microsoft.AspNetCore.Components.EventCallback.Factory.Create<Microsoft.AspNetCore.Components.Web.MouseEventArgs>(this, 
#nullable restore
#line 11 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Shared/NavMenu.razor"
                                        ToggleNavMenu

#line default
#line hidden
#nullable disable
            ));
            __builder.AddAttribute(13, "b-mrsrnueiu3");
            __builder.OpenElement(14, "ul");
            __builder.AddAttribute(15, "class", "nav flex-column");
            __builder.AddAttribute(16, "b-mrsrnueiu3");
            __builder.OpenElement(17, "li");
            __builder.AddAttribute(18, "class", "nav-item px-3");
            __builder.AddAttribute(19, "b-mrsrnueiu3");
            __builder.OpenComponent<Microsoft.AspNetCore.Components.Routing.NavLink>(20);
            __builder.AddAttribute(21, "class", "nav-link");
            __builder.AddAttribute(22, "href", "");
            __builder.AddAttribute(23, "Match", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<Microsoft.AspNetCore.Components.Routing.NavLinkMatch>(
#nullable restore
#line 14 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Shared/NavMenu.razor"
                                                     NavLinkMatch.All

#line default
#line hidden
#nullable disable
            ));
            __builder.AddAttribute(24, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder2) => {
                __builder2.AddMarkupContent(25, "<span class=\"oi oi-home\" aria-hidden=\"true\" b-mrsrnueiu3></span> Home\n            ");
            }
            ));
            __builder.CloseComponent();
            __builder.CloseElement();
            __builder.AddMarkupContent(26, "\n       \n        \n        ");
            __builder.OpenComponent<Microsoft.AspNetCore.Components.Authorization.AuthorizeView>(27);
            __builder.AddAttribute(28, "Roles", "Customer");
            __builder.AddAttribute(29, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment<Microsoft.AspNetCore.Components.Authorization.AuthenticationState>)((context) => (__builder2) => {
                __builder2.OpenElement(30, "li");
                __builder2.AddAttribute(31, "class", "nav-item px-3");
                __builder2.AddAttribute(32, "b-mrsrnueiu3");
                __builder2.OpenComponent<Microsoft.AspNetCore.Components.Routing.NavLink>(33);
                __builder2.AddAttribute(34, "class", "nav-link");
                __builder2.AddAttribute(35, "href", "MyShoppingCart");
                __builder2.AddAttribute(36, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder3) => {
                    __builder3.AddMarkupContent(37, "<span class=\"oi oi-list-rich\" aria-hidden=\"true\" b-mrsrnueiu3></span> Shopping Cart\n                ");
                }
                ));
                __builder2.CloseComponent();
                __builder2.CloseElement();
                __builder2.AddMarkupContent(38, "\n\n\n\n            ");
                __builder2.OpenElement(39, "li");
                __builder2.AddAttribute(40, "class", "nav-item px-3");
                __builder2.AddAttribute(41, "b-mrsrnueiu3");
                __builder2.OpenComponent<Microsoft.AspNetCore.Components.Routing.NavLink>(42);
                __builder2.AddAttribute(43, "class", "nav-link");
                __builder2.AddAttribute(44, "href", "Shop");
                __builder2.AddAttribute(45, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder3) => {
                    __builder3.AddMarkupContent(46, "<span class=\"oi oi-list-rich\" aria-hidden=\"true\" b-mrsrnueiu3></span> Shop\n                ");
                }
                ));
                __builder2.CloseComponent();
                __builder2.CloseElement();
            }
            ));
            __builder.CloseComponent();
            __builder.AddMarkupContent(47, "\n\n         \n        \n        ");
            __builder.OpenComponent<Microsoft.AspNetCore.Components.Authorization.AuthorizeView>(48);
            __builder.AddAttribute(49, "Roles", "WarehouseManager");
            __builder.AddAttribute(50, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment<Microsoft.AspNetCore.Components.Authorization.AuthenticationState>)((context) => (__builder2) => {
                __builder2.OpenElement(51, "li");
                __builder2.AddAttribute(52, "class", "nav-item px-3");
                __builder2.AddAttribute(53, "b-mrsrnueiu3");
                __builder2.OpenComponent<Microsoft.AspNetCore.Components.Routing.NavLink>(54);
                __builder2.AddAttribute(55, "class", "nav-link");
                __builder2.AddAttribute(56, "href", "warehousestocks");
                __builder2.AddAttribute(57, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder3) => {
                    __builder3.AddMarkupContent(58, "<span class=\"oi oi-list-rich\" aria-hidden=\"true\" b-mrsrnueiu3></span> Stocks\n            ");
                }
                ));
                __builder2.CloseComponent();
                __builder2.CloseElement();
            }
            ));
            __builder.CloseComponent();
            __builder.AddMarkupContent(59, "\n\n         \n\n        ");
            __builder.OpenComponent<Microsoft.AspNetCore.Components.Authorization.AuthorizeView>(60);
            __builder.AddAttribute(61, "Roles", "CompanyManager");
            __builder.AddAttribute(62, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment<Microsoft.AspNetCore.Components.Authorization.AuthenticationState>)((context) => (__builder2) => {
                __builder2.OpenElement(63, "li");
                __builder2.AddAttribute(64, "class", "nav-item px-3");
                __builder2.AddAttribute(65, "b-mrsrnueiu3");
                __builder2.OpenComponent<Microsoft.AspNetCore.Components.Routing.NavLink>(66);
                __builder2.AddAttribute(67, "class", "nav-link");
                __builder2.AddAttribute(68, "href", "ListOfProducts");
                __builder2.AddAttribute(69, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder3) => {
                    __builder3.AddMarkupContent(70, "<span class=\"oi oi-list-rich\" aria-hidden=\"true\" b-mrsrnueiu3></span> Products\n                ");
                }
                ));
                __builder2.CloseComponent();
                __builder2.CloseElement();
                __builder2.AddMarkupContent(71, "\n        \n        \n            ");
                __builder2.OpenElement(72, "li");
                __builder2.AddAttribute(73, "class", "nav-item px-3");
                __builder2.AddAttribute(74, "b-mrsrnueiu3");
                __builder2.OpenComponent<Microsoft.AspNetCore.Components.Routing.NavLink>(75);
                __builder2.AddAttribute(76, "class", "nav-link");
                __builder2.AddAttribute(77, "href", "ListOfUsers");
                __builder2.AddAttribute(78, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder3) => {
                    __builder3.AddMarkupContent(79, "<span class=\"oi oi-list-rich\" aria-hidden=\"true\" b-mrsrnueiu3></span> Users\n                ");
                }
                ));
                __builder2.CloseComponent();
                __builder2.CloseElement();
                __builder2.AddMarkupContent(80, "\n            \n              ");
                __builder2.OpenElement(81, "li");
                __builder2.AddAttribute(82, "class", "nav-item px-3");
                __builder2.AddAttribute(83, "b-mrsrnueiu3");
                __builder2.OpenComponent<Microsoft.AspNetCore.Components.Routing.NavLink>(84);
                __builder2.AddAttribute(85, "class", "nav-link");
                __builder2.AddAttribute(86, "href", "shop");
                __builder2.AddAttribute(87, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder3) => {
                    __builder3.AddMarkupContent(88, "<span class=\"oi oi-list-rich\" aria-hidden=\"true\" b-mrsrnueiu3></span> Shop\n                            ");
                }
                ));
                __builder2.CloseComponent();
                __builder2.CloseElement();
            }
            ));
            __builder.CloseComponent();
            __builder.CloseElement();
            __builder.CloseElement();
        }
        #pragma warning restore 1998
#nullable restore
#line 78 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Shared/NavMenu.razor"
       
    private bool collapseNavMenu = true;

    private string NavMenuCssClass => collapseNavMenu ? "collapse" : null;

    private void ToggleNavMenu()
    {
        collapseNavMenu = !collapseNavMenu;
    }


#line default
#line hidden
#nullable disable
    }
}
#pragma warning restore 1591

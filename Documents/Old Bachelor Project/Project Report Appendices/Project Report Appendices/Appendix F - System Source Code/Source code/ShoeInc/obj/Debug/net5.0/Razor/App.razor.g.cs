#pragma checksum "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/App.razor" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "61b44beacc5e33e8143c2ee2a2c9a45e1e287c3e"
// <auto-generated/>
#pragma warning disable 1591
namespace ShoeInc
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
    public partial class App : Microsoft.AspNetCore.Components.ComponentBase
    {
        #pragma warning disable 1998
        protected override void BuildRenderTree(Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder)
        {
            __builder.OpenComponent<Microsoft.AspNetCore.Components.Authorization.CascadingAuthenticationState>(0);
            __builder.AddAttribute(1, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder2) => {
                __builder2.OpenComponent<Microsoft.AspNetCore.Components.Routing.Router>(2);
                __builder2.AddAttribute(3, "AppAssembly", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.Reflection.Assembly>(
#nullable restore
#line 2 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/App.razor"
                          typeof(App).Assembly

#line default
#line hidden
#nullable disable
                ));
                __builder2.AddAttribute(4, "Found", (Microsoft.AspNetCore.Components.RenderFragment<Microsoft.AspNetCore.Components.RouteData>)((routeData) => (__builder3) => {
                    __builder3.OpenComponent<Microsoft.AspNetCore.Components.Authorization.AuthorizeRouteView>(5);
                    __builder3.AddAttribute(6, "RouteData", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<Microsoft.AspNetCore.Components.RouteData>(
#nullable restore
#line 4 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/App.razor"
                                            routeData

#line default
#line hidden
#nullable disable
                    ));
                    __builder3.AddAttribute(7, "DefaultLayout", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.Type>(
#nullable restore
#line 4 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/App.razor"
                                                                       typeof(MainLayout)

#line default
#line hidden
#nullable disable
                    ));
                    __builder3.CloseComponent();
                    __builder3.AddMarkupContent(8, "\n            ");
                    __builder3.OpenElement(9, "FocusOnNavigate");
                    __builder3.AddAttribute(10, "RouteData", 
#nullable restore
#line 5 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/App.razor"
                                         routeData

#line default
#line hidden
#nullable disable
                    );
                    __builder3.AddAttribute(11, "Selector", "h1");
                    __builder3.CloseElement();
                }
                ));
                __builder2.AddAttribute(12, "NotFound", (Microsoft.AspNetCore.Components.RenderFragment)((__builder3) => {
                    __builder3.OpenElement(13, "PageTitle");
                    __builder3.AddContent(14, "Not found");
                    __builder3.CloseElement();
                    __builder3.AddMarkupContent(15, "\n            ");
                    __builder3.OpenComponent<Microsoft.AspNetCore.Components.LayoutView>(16);
                    __builder3.AddAttribute(17, "Layout", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.Type>(
#nullable restore
#line 9 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/App.razor"
                                 typeof(MainLayout)

#line default
#line hidden
#nullable disable
                    ));
                    __builder3.AddAttribute(18, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder4) => {
                        __builder4.AddMarkupContent(19, "<p role=\"alert\">Sorry, there\'s nothing at this address.</p>");
                    }
                    ));
                    __builder3.CloseComponent();
                }
                ));
                __builder2.CloseComponent();
            }
            ));
            __builder.CloseComponent();
        }
        #pragma warning restore 1998
    }
}
#pragma warning restore 1591

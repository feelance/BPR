namespace ShoeInc.Data
{
    public class LoginCredentials
    {
        public string email { get; set; } = "";

        public User user { get; set; }

        public bool IsLoggedIn { get; set; } = false;

        public LoginCredentials()
        {
        
        }
        public LoginCredentials(string email)
        {
            this.email = email; 
        }

        public void SetUser(User u)
        {
            user = u;
        }

        public User GetUser()
        {
            if (IsLoggedIn)
            {
                return user;    
            } 
            return null;

        }

        public void LogOut()
        {
            IsLoggedIn = false;
            user = null;
            email = null;
        }
            
    }
}
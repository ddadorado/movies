import SwiftUI

struct LoginView: View {
    @State private var username = ""
    @State private var password = ""
    
    // This State is used to control the navigation to the next screen
    @State private var isNavigated = false
    
    var body: some View {
        NavigationView {
            VStack {
                Text("Login")
                    .font(.title)
                    .bold()
                
                TextField("Username", text: $username)
                    .padding()
                    .background(RoundedRectangle(cornerRadius: 10).stroke(Color.gray, lineWidth: 2))
                
                Spacer().frame(height: 10)
                SecureField("Password", text: $password)
                    .padding()
                    .background(RoundedRectangle(cornerRadius: 10).stroke(Color.gray, lineWidth: 2))
                
                Spacer().frame(height: 30)
                
                NavigationLink(destination: MenuView(), isActive: $isNavigated) {
                    EmptyView() // This is hidden, and the navigation is controlled by isNavigated
                }
                
                Button(action: {
                    // You can perform login here
                    // For now, let's navigate to the NextScreen
                    isNavigated = true
                }) {
                    Text("Login")
                        .foregroundColor(.white)
                        .padding()
                        .frame(maxWidth: .infinity)
                        .background(Color.blue)
                        .cornerRadius(8)
                }
                
                Button(action: {
                    // Do nothing
                }) {
                    Text("Register")
                        .foregroundColor(.white)
                        .padding()
                        .frame(maxWidth: .infinity)
                        .background(Color.green)
                        .cornerRadius(8)
                }
            }
            .padding()
        }
    }
}

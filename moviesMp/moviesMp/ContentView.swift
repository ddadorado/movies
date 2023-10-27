import SwiftUI
import shared

struct ContentView: View {
    @State private var isButtonPressed = false

    var body: some View {
        NavigationView {
            ZStack {
                Color.clear // Set a clear background
                VStack {
                    Spacer().frame(height: 100)
                    Text("Application")
                        .bold()
                        .underline()
                        .font(.system(size: 24))
                    Spacer() // Push the button to the bottom
                    NavigationLink(destination: LoginView(), isActive: $isButtonPressed) {
                        EmptyView()
                    }
                    Button(action: {
                        // Handle button press here
                        isButtonPressed.toggle()
                    }) {
                        Text("Start")
                            .foregroundColor(.white) // Set text color
                            .padding()
                            .background(Color.purple) // Set the background color for the button
                            .cornerRadius(8) // Add corner radius for a rounded look
                    }
                }
                if isButtonPressed {
                    //Text("Button was pressed!")
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

import SwiftUI

@main
struct iOSApp: App {
   // @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
//    @StateObject private var dataController = DataController()
//    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    let persistenceController = PersistenceController.shared

	var body: some Scene {
		WindowGroup {
			ContentView()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
                //.environment(.\managedObjectContext, dataController.container.viewContext)
                //.environment(\.managedObjectContext, dataController.container.viewContext)
                //.environmentObject(dataController)
		}
	}
}

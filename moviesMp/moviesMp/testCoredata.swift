//import CoreData
//import shared
//import SwiftUI
//
//class CoreDataManager {
//    static let shared = CoreDataManager()
//
//    // This managed object context is used to interact with CoreData
//    private let managedObjectContext = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
//
//    func saveFavoriteMovie(movie: MovieData) {
//        let favoriteMovie = FavoriteMovie(context: managedObjectContext)
//        favoriteMovie.id = Int32(movie.id)
//        favoriteMovie.title = movie.title
//        favoriteMovie.imageURL = movie.imageResourceId
//        favoriteMovie.rating = movie.rating
//        favoriteMovie.isFavorite = true
//
//        do {
//            try managedObjectContext.save()
//        } catch {
//            print("Error saving favorite movie: \(error)")
//        }
//    }
//
//    func fetchFavoriteMovies() -> [MovieData] {
//        let fetchRequest: NSFetchRequest<FavoriteMovie> = FavoriteMovie.fetchRequest()
//        fetchRequest.predicate = NSPredicate(format: "isFavorite == true")
//
//        do {
//            let favoriteMovies = try managedObjectContext.fetch(fetchRequest)
//            // Convert the CoreData objects to MovieData
//            return favoriteMovies.map { movie in
//                return MovieData(id: Int(movie.id), title: movie.title ?? "", imageResourceId: movie.imageURL ?? "", rating: movie.rating, isFavorite: true)
//            }
//        } catch {
//            print("Error fetching favorite movies: \(error)")
//            return []
//        }
//    }
//
//    func deleteFavoriteMovie(movie: MovieData) {
//        let fetchRequest: NSFetchRequest<FavoriteMovie> = FavoriteMovie.fetchRequest()
//        fetchRequest.predicate = NSPredicate(format: "id == %d", Int32(movie.id))
//
//        do {
//            let matchingMovies = try managedObjectContext.fetch(fetchRequest)
//            for movie in matchingMovies {
//                managedObjectContext.delete(movie)
//            }
//            try managedObjectContext.save()
//        } catch {
//            print("Error deleting favorite movie: \(error)")
//        }
//    }
//}
//

////
////  CoredatabaseManager2.swift
////  moviesMp
////
////  Created by Training Sj on 10/26/23.
////  Copyright © 2023 orgName. All rights reserved.
////
//
//import SwiftUI
//import shared
//import CoreData
//
//
//class CoredatabaseManager2:NSObject, DatabaseManager {
//     //Core Data stack setup
//    //private let managedObjectContext = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
//    //private let managedObjectContext = (UIApplication.shared.delegate as! moviesMp.AppDelegate).persistentContainer.viewContext
////    let persistentContainer = NSPersistentContainer(name: "MyApp")
////    let managedObjectContext = persistentContainer.viewContext
//    
//    
////    private lazy var persistentContainer: NSPersistentContainer = {
////            let persistentContainer = NSPersistentContainer(name: "MoviesModel")
////            return persistentContainer
////        }()
////
////        private var managedObjectContext: NSManagedObjectContext {
////            return persistentContainer.viewContext
////        }
//
//    private lazy var managedObjectModel: NSManagedObjectModel = {
//            let managedObjectModel = NSManagedObjectModel()
//
//          
//            //managedObjectModel.entities = [MovieEntity]
//
//            return managedObjectModel
//        }()
//
//        private lazy var persistentContainer: NSPersistentContainer = {
//            let persistentContainer = NSPersistentContainer(name: "MoviesModel", managedObjectModel: managedObjectModel)
//            return persistentContainer
//        }()
//
//        private var managedObjectContext: NSManagedObjectContext {
//            return persistentContainer.viewContext
//        }
//
//    // Fetch movies from Core Data
//    func getMovies() -> [MovieData] {
//        // Implement the Core Data fetch logic here
//        // Return an array of MovieData objects
//        let movie1 = MovieData(imageResourceId: "image1", id: 1, title: "Movie 1", rating: 4.5, isFavorite: true)
//        let movie2 = MovieData(imageResourceId: "image2", id: 2, title: "Movie 2", rating: 3.8, isFavorite: false)
//        let movie3 = MovieData(imageResourceId: "image3", id: 3, title: "Movie 3", rating: 4.0, isFavorite: true)
//                return [movie1, movie2, movie3]    }
//
//    // Save a movie to Core Data
//    func saveMovie(movie: MovieData) {
//         //Implement the Core Data save logic here
//        let favoriteMovie = MovieEntity(context: managedObjectContext)
//
//                // Set the properties of the FavoriteMovie object
//                favoriteMovie.id = movie.id
//                favoriteMovie.title = movie.title
//                favoriteMovie.imageResourceId = movie.imageResourceId
//                favoriteMovie.rating = movie.rating
//                favoriteMovie.isFavorite = true
//
//                // Save the FavoriteMovie object to the database
//                do {
//                    try managedObjectContext.save()
//                } catch {
//                    print("Error saving favorite movie: \(error)")
//                }
//     }
//
//    // Delete a movie from Core Data
//    func deleteMovie(movie: MovieData) {
//        // Implement the Core Data delete logic here
//    }
//}

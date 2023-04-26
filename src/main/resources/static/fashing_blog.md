```mermaid
sequenceDiagram
    participant User
    participant UserController
    participant PostController
    participant LikeController
    participant CommentController
    participant CategoryController

    User->UserController: Registers or logs in
    UserController->User: Returns success/failure

    User->PostController: Creates a new post
    PostController->User: Returns success/failure
    User->PostController: Views all posts
    PostController->User: Returns all posts
    User->PostController: Views posts by category
    PostController->User: Returns posts by category
    User->PostController: Edits a post
    PostController->User: Returns success/failure
    User->PostController: Deletes a post
    PostController->User: Returns success/failure

    User->LikeController: Likes a post
    LikeController->User: Returns success/failure

    User->CommentController: Creates a new comment
    CommentController->User: Returns success/failure
    User->CommentController: Views all comments
    CommentController->User: Returns all comments
    User->CommentController: Deletes a comment
    CommentController->User: Returns success/failure

    User->CategoryController: Creates a new category
    CategoryController->User: Returns success/failure
    User->CategoryController: Views all categories
    CategoryController->User: Returns all categories
    User->CategoryController: Edits a category
    CategoryController->User: Returns success/failure
    User->CategoryController: Deletes a category
    CategoryController->User: Returns success/failure
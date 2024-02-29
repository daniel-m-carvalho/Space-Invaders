# Space Invaders

| ![Space Invaders](/docs/invaders.png) |
|:-------------------------------------:|
|           _Space Invaders_            |

> The purpose of this project was to develop a game in Kotlin using
> the [CanvasLib](https://github.com/palex65/CanvasLib) library, which supports canvas-based graphics programming.

## Requirements

- JRE 1.4.0 or above (for running the application);
- JDK 17 or above (for development purposes).

### Points

There are **55** aliens initially in the central block:

- 2 lines at the top with 11 aliens each of the type **SQUID**
  (in _yellow_);
- 2 lines in the middle with 11 aliens each of the type **CRAB**
  (in _light blue_);
- 1 line at the bottom with 11 aliens of the type **OCTOPUS** (in _light green_).

There is also a **UFO** (in _red_) type. But it was not included in this work because of lack of time.

Each type of alien has a different score value, as shown in the following table:

| Alien Type | Points |
|:----------:|:------:|
|    UFO     |  100   |
|    CRAB    |   20   |
|  OCTOPUS   |   10   |
|   SQUID    |   30   |

### Animations

To enhance the game's visual appeal and create a more dynamic experience, animations were implemented. These animations
utilize a [sprite sheet](https://gamedevelopment.tutsplus.com/an-introduction-to-spritesheet-animation--gamedev-13099t),
which is a composite image containing multiple frames. By displaying the frames sequentially
with a specific delay, the illusion of movement is created.

Each frame in the sprite sheet represents an animation step, and it advances as the animation updates. Multiple
animation steps can be defined for a single image, allowing for different animations to be displayed using the same
image, as is the case for the aliens.

When the desired animations for an image are completed, the animation step is **resetted to 0**. For instance, if the
sprite sheet has two columns representing different animations for a **SQUID type**, with the first animation step being
0 and the second being 1, the third animation step will go back to 0. This cycling behavior is achieved by using the
**modulus operator**, which wraps the animation step based on the number of columns in the sprite sheet.

The following image shows the sprite
sheet used for the aliens' animation with the respective dimensions of each frame.

| ![Aliens Sprite Sheet](/src/main/resources/invaders.png) |
|:--------------------------------------------------------:|
|                  _Aliens Sprite Sheet_                   |

### Hitboxes

In order to **detect collisions** between the different entities of the game, hitboxes were created.
A **hitbox** is a defined **area that surrounds an entity**, which is used to determine if it has collided with another
entity.

### Spaceship

The spaceship's hitbox **is composed of 4 rectangles**, which are used to detect collisions with the aliens'
projectiles.

Below, on the left, is the image of the spaceship used in the game, and on the right, the hitbox created for it.

| ![Spaceship](/docs/spaceship-hitbox.png) |
|:----------------------------------------:|
|               _Spaceship_                |

To create the rectangles, it was necessary to know the x and y coordinates of the upper left corner of each one,
as well as the width and length.
The coordinates of the ship are represented in the previous image by the intersection of two straight lines that were
used
to find them.

| ![Spaceship Offsets](/docs/spaceship-offsets.png) |
|:-------------------------------------------------:|
|                _Spaceship Offsets_                |


> [!Important]
> It's worth mentioning that the project was developed in a short period of time. It was not intended to be
> maintained or scaled, as the main goal was to learn and practice the Kotlin programming language as a way to introduce
> programming basic concepts to students.

---

Instituto Superior de Engenharia de Lisboa<br>
BSc in Computer Science and Engineering<br>
Programming<br>
Winter Semester of 2021/2022

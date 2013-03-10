package org.helgoboss.capsule

/**
 * Provides convenient `onStart` and `onStop` methods which the end user can use for adding start and stop logic
 * to the current scope ad hoc.
 */
trait CapsuleConvenience {
  protected def capsuleContext: CapsuleContext

  /**
   * Adds the given start logic. It's executed immediately.
   *
   * @param f start logic
   */
  def onStart(f: => Unit) {
    // Create a capsule which just contains start logic
    val capsule = new Capsule {
      def start() {
        f
      }
      def stop() {}
    }

    // Add the capsule to the current scope
    capsuleContext.addCapsule(capsule)
  }

  /**
   * Adds the given stop logic. Tthe given function will be executed when the current scope is stopped.
   *
   * @param f stop logic
   */
  def onStop(f: => Unit) {
    // Create a capsule which just contains stop logic
    val capsule = new Capsule {
      def start() {}
      def stop() {
        f
      }
    }

    // Add the capsule to the current scope
    capsuleContext.addCapsule(capsule)
  }
}

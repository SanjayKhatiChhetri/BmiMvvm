import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BMIViewModel : ViewModel() {
    var heightState = mutableStateOf("1.88")
        private set // Make the setter private to ensure encapsulation

    var weightState = mutableStateOf("90")
        private set

    // Now, let's introduce a mutable state for storing the calculated BMI.
    // Initially, it could be null or some default value indicating that BMI hasn't been calculated yet.
    var bmiState = mutableStateOf<Float?>(null)
        private set

    // Private method to calculate BMI
    private fun calculateBMI(height: Float, weight: Float): Float {
        return if (height > 0 && weight > 0) {
            weight / (height * height)
        } else {
            0f
        }
    }

    // Public function to trigger BMI calculation. This ensures encapsulation of the
    // calculation logic while allowing external components to request a BMI update.
    // Public methods to update height and weight. They also trigger BMI calculation.
    fun updateHeight(newHeight: String) {
        heightState.value = newHeight
        recalculateBMI()
    }

    fun updateWeight(newWeight: String) {
        weightState.value = newWeight
        recalculateBMI()
    }

    // Helper method to recalculate and update the BMI state.
    private fun recalculateBMI() {
        val height = heightState.value.toFloatOrNull() ?: 0f
        val weight = weightState.value.toFloatOrNull() ?: 0f
        bmiState.value = calculateBMI(height, weight)
    }
}


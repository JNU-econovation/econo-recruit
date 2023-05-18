const ApplicantSearchComponent = () => {
  return (
    <form className="flex items-center bg-[#F9FBFF] py-2 px-4 rounded-2xl gap-2">
      <button>
        <img src="/search-icon.svg" alt="search" />
      </button>
      <input className="p-2 bg-transparent outline-none" type="text" placeholder="search" />
    </form>
  )
}

export default ApplicantSearchComponent

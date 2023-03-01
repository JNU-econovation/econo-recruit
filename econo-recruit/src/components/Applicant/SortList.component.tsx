type ApplicantSortListComponent = {
  sortList: { type: string; string: string }[]
}

const ApplicantSortListComponent = ({ sortList }: ApplicantSortListComponent) => {
  return <div>dropdown</div>
  //   return (
  //     <select className="appearance-none p-2 rounded-lg bg-[#F9FBFF]" name="sortList" id="sortList">
  //       {sortList.map((sort) => (
  //         <option value={sort.type}>{sort.string}</option>
  //       ))}
  //     </select>
  //   )
}

export default ApplicantSortListComponent

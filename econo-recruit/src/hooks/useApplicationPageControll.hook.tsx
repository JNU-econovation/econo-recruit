import { ApplicationNavbarIndexState } from '@/storage/Application/Application.atom';
import { useSetAtom } from 'jotai';

const useApplicationPageControll = () => {
  const setPage = useSetAtom(ApplicationNavbarIndexState);
  const goNextPage = () => {
    setPage((page) => page + 1);
  };
  const goPrevPage = () => {
    setPage((page) => (page - 1 < 0 ? 0 : page - 1));
  };
  return { goNextPage, goPrevPage };
};

export default useApplicationPageControll;

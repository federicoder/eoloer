import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { NotePersonaDetailComponent } from 'app/entities/note-persona/note-persona-detail.component';
import { NotePersona } from 'app/shared/model/note-persona.model';

describe('Component Tests', () => {
  describe('NotePersona Management Detail Component', () => {
    let comp: NotePersonaDetailComponent;
    let fixture: ComponentFixture<NotePersonaDetailComponent>;
    const route = ({ data: of({ notePersona: new NotePersona(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [NotePersonaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NotePersonaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NotePersonaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load notePersona on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.notePersona).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
